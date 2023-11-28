import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Pageable } from '../core/model/page/Pageable';
import { Prestamo } from './model/Prestamo';
import { PrestamoPage } from './model/PrestamoPage';
import { HttpClient } from '@angular/common/http';
import { Client } from '../client/model/Client';
import { MatDialog } from '@angular/material/dialog';
import { DialogConfirmationComponent } from '../core/dialog-confirmation/dialog-confirmation.component';
import { ErrorMessageComponent } from '../core/error-message/error-message.component';

@Injectable({
    providedIn: 'root'
})
export class PrestamoService {

    constructor(
        private http: HttpClient,
        public dialog: MatDialog
    ) { }

    getPrestamos(pageable: Pageable): Observable<PrestamoPage> {
        return this.http.post<PrestamoPage>('http://localhost:8080/prestamo', {pageable:pageable});
    
    }

    savePrestamo(prestamo: Prestamo): Observable<void> {

        const fechaActual = new Date();
        if(prestamo.fechaComienzo < fechaActual){
            this.dialog.open(ErrorMessageComponent, {
                data: { 
                    title: "No se ha podido crear el préstamo", 
                    description: "La fecha de comienzo no puede ser anterior a la fecha actual." 
                }
            })
        }else{

        
            if(prestamo.fechaComienzo> prestamo.fechaDevolucion ){
                this.dialog.open(ErrorMessageComponent, {
                    data: { 
                        title: "No se ha podido crear el préstamo", 
                        description: "La fecha de comienzo es posterior a la fecha de fin." 
                    }
                })
            }else{
                const fechaComienzo = new Date(prestamo.fechaComienzo).getDate();
                const fechaDevolucion = new Date(prestamo.fechaDevolucion).getDate();

                // (1000*60*60*24) --> milisegundos -> segundos -> minutos -> horas -> días
                const plazo = (fechaDevolucion-fechaComienzo)/ (1000*60*60*24);

                if(Math.abs(plazo) > 14){
                    this.dialog.open(DialogConfirmationComponent, {
                        data: { 
                            title: "No se ha podido crear el préstamo", 
                            description: "El plazo del préstamo es superior a 14 días." 
                        }
                    })
                }else{
                    let url = 'http://localhost:8080/prestamo';
                    return this.http.put<void>(url, prestamo);
                }
                
            }
        }
    }

    deletePrestamo(idPrestamo : number): Observable<void> {
        return this.http.delete<void>('http://localhost:8080/prestamo/'+idPrestamo);
    
    }    

    getClients(): Observable<Client[]>{
        return this.http.get<Client[]>('http://localhost:8080/client');
    }

    getPrestamosFiltrados(idJuego?: number, idCliente?: number, fecha?: Date, pageable?: Pageable): Observable<PrestamoPage>{
        
        return this.http.post<PrestamoPage>('http://localhost:8080/prestamo', {
            idJuego:idJuego, 
            idCliente:idCliente, 
            fecha:fecha, 
            pageable
        });
    }
}