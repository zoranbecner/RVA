import { Status } from '../../../models/status';
import { StatusService } from '../../../services/status.service';
import { Component, OnInit, Inject } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-status-dialog',
  templateUrl: './status-dialog.component.html',
  styleUrls: ['./status-dialog.component.css']
})
export class StatusDialogComponent implements OnInit {
  public flag: number;
  constructor(public snackBar: MatSnackBar,
              public dialogRef: MatDialogRef<StatusDialogComponent>,
              @Inject (MAT_DIALOG_DATA) public data: Status,
              public statusService: StatusService
  ) { }

  ngOnInit() {
  }

  public add(): void{
    this.statusService.addStatus(this.data);
    this.snackBar.open('Uspešno dodat status: ' + this.data.naziv, 'U redu', {
      duration: 2500
    });
  }

  public update(): void{
    this.statusService.updateStatus(this.data);
    this.snackBar.open('Uspešno modifikovan status: ' + this.data.naziv, 'U redu', {
      duration: 1500
    });
  }

  public delete(): void{
    console.log('brisem status sa id: ' + this.data.id);
    this.statusService.deleteStatus(this.data.id);
    this.snackBar.open('Uspešno obrisan status: ' + this.data.naziv, 'U redu', {
      duration: 1500
    });
  }

  public cancel(): void{
    this.dialogRef.close();
    this.snackBar.open('Odustali ste', 'U redu', {
      duration: 500
    });
  }
}
