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

        let url = 'http://localhost:8080/prestamo';
        return this.http.put<void>(url, prestamo);
                
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