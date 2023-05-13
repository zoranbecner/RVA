import { Component, OnInit, Inject } from '@angular/core';
import { Fakultet } from 'src/app/models/fakultet';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Departman } from 'src/app/models/departman';
import { DepartmanService } from 'src/app/services/departman.service';
import { FakultetService } from 'src/app/services/fakultet.service';

@Component({
  selector: 'app-departman-dialog',
  templateUrl: './departman-dialog.component.html',
  styleUrls: ['./departman-dialog.component.css']
})
export class DepartmanDialogComponent implements OnInit {
  fakulteti: Fakultet[];
  public flag: number;

  constructor(public snackBar: MatSnackBar,
              public dialogRef: MatDialogRef<DepartmanDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: Departman,
              public departmanService: DepartmanService,
              public fakultetService: FakultetService
  ) { }

  ngOnInit() {
    this.fakultetService.getAllFakultet().subscribe(fakulteti =>
      this.fakulteti = fakulteti
    );
  }
  compareTo(a, b) {
    return a.id == b.id;
  }

  public add(): void {
    this.data.id = -1;
    this.departmanService.addDepartman(this.data);
    this.snackBar.open('Uspešno dodat departman', 'U redu', { duration: 1500 });
  }

  public update(): void {
    this.departmanService.updateDepartman(this.data);
    this.snackBar.open('Uspešno modifikovan deartman', 'U redu', { duration: 1500 });
  }

  public delete(): void {
    this.departmanService.deleteDepartman(this.data.id);
    this.snackBar.open('Uspešno obrisan departman', 'U redu', { duration: 1500 });
  }

  public cancel(): void {
    this.dialogRef.close();
    this.snackBar.open('Odustali ste', 'U redu', { duration: 500 });
  }
}
