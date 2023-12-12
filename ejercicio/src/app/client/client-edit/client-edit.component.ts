import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ClientService } from '../client.service';
import { Client } from '../model/Client';
import { MatDialog } from '@angular/material/dialog';
import { ErrorMessageComponent } from '../../core/error-message/error-message.component';


@Component({
  selector: 'app-client-edit',
  templateUrl: './client-edit.component.html',
  styleUrls: ['./client-edit.component.scss']
})
export class ClientEditComponent implements OnInit {

  client : Client;

  constructor(
    public dialogRef: MatDialogRef<ClientEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private clientService: ClientService,
    public dialog: MatDialog
  ) { }

  ngOnInit(): void {
    if (this.data.client != null) {
      this.client = Object.assign({}, this.data.client);
    }
    else {
      this.client = new Client();
    }
  }

  onSave() {
    this.clientService.saveClient(this.client).subscribe(result => {
      this.dialogRef.close();
    },
    
    error=>{
      this.dialog.open(ErrorMessageComponent, {
          data: { 
              title: "No se ha podido crear el cliente", 
              description: "El cliente ya está dado de alta." 
          }
      })
  }
    );    
  }  

  onClose() {
    this.dialogRef.close();
  }

}