import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { PrestamoService } from '../prestamo.service';
import { Prestamo } from '../model/Prestamo';
import { Game } from 'src/app/game/model/Game';
import { Client } from 'src/app/client/model/Client';
import { GameService } from 'src/app/game/game.service';
import { ClientService } from 'src/app/client/client.service';
import { MatDialog } from '@angular/material/dialog';
import { ErrorMessageComponent } from '../../core/error-message/error-message.component';


@Component({
selector: 'app-prestamo-edit',
templateUrl: './prestamo-edit.component.html',
styleUrls: ['./prestamo-edit.component.scss']
})
export class PrestamoEditComponent implements OnInit {

    prestamo : Prestamo;
    listaJuegos: Game[];
    listaClientes: Client[];

    constructor(
        public dialogRef: MatDialogRef<PrestamoEditComponent>,
        @Inject(MAT_DIALOG_DATA) public data: any,
        private prestamoService: PrestamoService,
        private gameService: GameService,
        private clientService: ClientService,
        public dialog: MatDialog
    ) { }

    ngOnInit(): void {
        if (this.data.prestamo != null) {
            this.prestamo = Object.assign({}, this.data.prestamo);
        }
        else {
            this.prestamo = new Prestamo();
        }

        this.cargarClientes();
        this.cargarJuegos();
    }

    onSave() {
        console.log('Datos a enviar:', this.prestamo);
        this.prestamoService.savePrestamo(this.prestamo).subscribe(result =>  {
            this.dialogRef.close();
        },

        error=>{
            this.dialog.open(ErrorMessageComponent, {
                data: { 
                    title: "No se ha podido crear el préstamo", 
                    description: "Revisa la disponibilidad del  juego, y si el cliente ya tiene asignado un préstamo en la fecha solicitada." 
                }
            })
        }
        ); 
    }  

    onClose() {
        this.dialogRef.close();
    }

    cargarClientes() {
        this.clientService.getClients().subscribe(
            (listaClientes: Client[]) => {
                this.listaClientes = listaClientes;
            },
            error => {
                console.error('Error cargando clientes', error);
            }
        );
    }

    cargarJuegos() {
        this.gameService.getGames().subscribe(
            (listaJuegos: Game[]) => {
                this.listaJuegos = listaJuegos;
            },
            error => {
                console.error('Error cargando juegos', error);
            }
        );
    }


}