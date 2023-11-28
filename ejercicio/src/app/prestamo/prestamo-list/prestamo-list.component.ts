import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { DialogConfirmationComponent } from 'src/app/core/dialog-confirmation/dialog-confirmation.component';
import { Pageable } from 'src/app/core/model/page/Pageable';
import { PrestamoEditComponent } from '../prestamo-edit/prestamo-edit.component';
import { PrestamoService } from '../prestamo.service';
import { Prestamo } from '../model/Prestamo';
import { Game } from 'src/app/game/model/Game';
import { Client } from 'src/app/client/model/Client';
import { GameService } from 'src/app/game/game.service';
import { ClientService } from 'src/app/client/client.service';

@Component({
selector: 'app-prestamo-list',
templateUrl: './prestamo-list.component.html',
styleUrls: ['./prestamo-list.component.scss']
})
export class PrestamoListComponent implements OnInit {

    pageNumber: number = 0;
    pageSize: number = 5;
    totalElements: number = 0;

    dataSource = new MatTableDataSource<Prestamo>();
    displayedColumns: string[] = ['id', 'title', 'name', 'fechaComienzo', 'fechaDevolucion', 'action'];

    listaJuegos: Game[];
    listaClientes: Client[];

    filterTitle : Game = null;
    filterClient : Client = null;
    filterDate: Date = null;

    constructor(
        private prestamoService: PrestamoService,
        public dialog: MatDialog,
        private gameService: GameService,
        private clientService: ClientService,
    ) { }

    ngOnInit(): void {

        this.gameService.getGames().subscribe(
            games => this.listaJuegos = games
        );

        this.clientService.getClients().subscribe(
            clients => this.listaClientes = clients
        );

        this.loadPage();
    }

    loadPage(event?: PageEvent) {

        let pageable : Pageable =  {
            pageNumber: this.pageNumber,
            pageSize: this.pageSize,
            sort: [{
                property: 'id',
                direction: 'ASC'
            }]
        }

        if (event != null) {
            pageable.pageSize = event.pageSize
            pageable.pageNumber = event.pageIndex;
        }

        this.prestamoService.getPrestamos(pageable).subscribe(data => {
            this.dataSource.data = data.content;
            this.pageNumber = data.pageable.pageNumber;
            this.pageSize = data.pageable.pageSize;
            this.totalElements = data.totalElements;
        });

    }  

    createPrestamo() {      
        const dialogRef = this.dialog.open(PrestamoEditComponent, {
            data: {}
        });

        dialogRef.afterClosed().subscribe(result => {
            this.ngOnInit();
        });      
    }  

    editPrestamo(prestamo: Prestamo) {    
        const dialogRef = this.dialog.open(PrestamoEditComponent, {
            data: { prestamo: prestamo }
        });

        dialogRef.afterClosed().subscribe(result => {
            this.ngOnInit();
        });    
    }

    deletePrestamo(prestamo: Prestamo) {    
        const dialogRef = this.dialog.open(DialogConfirmationComponent, {
            data: { title: "Eliminar préstamo", description: "Atención si borra el préstamo se perderán sus datos.<br> ¿Desea eliminar el préstamo?" }
        });

        dialogRef.afterClosed().subscribe(result => {
            if (result) {
                this.prestamoService.deletePrestamo(prestamo.id).subscribe(result =>  {
                    this.ngOnInit();
                }); 
            }
        });
    }  

    onCleanFilter(): void {
        this.filterTitle = null;
        this.filterClient = null;
        this.filterDate = null;

        this.onSearch();
    }

    onSearch(): void {

        let idJuego = this.filterTitle != null ? this.filterTitle.id : null;
        let idCliente = this.filterClient != null ? this.filterClient.id : null;
        let fecha = this.filterDate != null ? this.filterDate: null;

        const pageable : Pageable =  {
            pageNumber: this.pageNumber,
            pageSize: this.pageSize,
            sort: [{
                property: 'id',
                direction: 'ASC'
            }]
        }

        this.prestamoService.getPrestamosFiltrados(idJuego, idCliente, fecha, pageable).subscribe(
            data => {
                this.dataSource.data = data.content;
                this.pageNumber = data.pageable.pageNumber;
                this.pageSize = data.pageable.pageSize;
                this.totalElements = data.totalElements;
            }
        )


    }
}