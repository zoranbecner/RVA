import { Component, OnInit, Inject } from '@angular/core';
import { Status } from 'src/app/models/status';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Student } from 'src/app/models/student';
import { StudentService } from 'src/app/services/student.service';
import { StatusService } from 'src/app/services/status.service';

@Component({
  selector: 'app-student-dialog',
  templateUrl: './student-dialog.component.html',
  styleUrls: ['./student-dialog.component.css']
})
export class StudentDialogComponent implements OnInit {
  statusi: Status[];
  public flag: Number;

  constructor(public snackBar: MatSnackBar,
              public dialogRef: MatDialogRef<StudentDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: Student,
              public studentService: StudentService,
              public statusService: StatusService
  ) { }


  ngOnInit() {
    this.statusService.getAllStatus().subscribe(statusi =>
      this.statusi = statusi
    );
  }

  public add(): void {
    this.data.id = -1;
    this.studentService.addStudent(this.data);
    this.snackBar.open('Uspešno dodat student', 'U redu', { duration: 2500 });
  }

  public update(): void {
    this.studentService.updateStudent(this.data);
    this.snackBar.open('Uspešno modifikovan student', 'U redu', { duration: 2500 });
  }

  public delete(): void {
    this.studentService.deleteStudent(this.data.id);
    this.snackBar.open('Uspešno obrisan student', 'U redu', { duration: 2000 });
  }

  public cancel(): void {
    this.dialogRef.close();
    this.snackBar.open('Odustali ste', 'U redu', { duration: 1000 });
  }

  compareTo(a, b) {
    return a.id === b.id;
  }
}
