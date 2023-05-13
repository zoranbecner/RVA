import { Component, OnInit, Input, ViewChild, OnChanges } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Student } from 'src/app/models/student';
import { Departman } from 'src/app/models/departman';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { StudentService } from 'src/app/services/student.service';
import { MatDialog } from '@angular/material/dialog';
import { Status } from 'src/app/models/status';
import { StudentDialogComponent } from '../dialogs/student-dialog/student-dialog.component';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})
export class StudentComponent implements OnInit, OnChanges {
  displayedColumns = ['id', 'ime', 'prezime', 'brojIndeksa', 'status', 'departman', 'actions'];
  dataSource: MatTableDataSource<Student>;

  @Input() selektovanaDepartman: Departman;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(public studentService: StudentService,
              public dialog: MatDialog) { }

  ngOnInit() {

  }

  ngOnChanges() {
    if (this.selektovanaDepartman.id) {
      console.log(this.selektovanaDepartman.id);
      debugger;
      this.loadData();

    }
  }

  public loadData() {
    this.studentService.studentPoDepartmanuId(this.selektovanaDepartman.id)
      .subscribe(data => {
        this.dataSource = new MatTableDataSource(data);
        // pretraga po nazivu ugnježdenog objekta
        this.dataSource.filterPredicate = (data, filter: string) => {
          const accumulator = (currentTerm, key) => {
            return key === 'status' ? currentTerm + data.status.naziv : currentTerm + data[key];
          };
          const dataStr = Object.keys(data).reduce(accumulator, '').toLowerCase();
          const transformedFilter = filter.trim().toLowerCase();
          return dataStr.indexOf(transformedFilter) !== -1;
        };

        // sortiranje po nazivu ugnježdenog objekta
        this.dataSource.sortingDataAccessor = (data, property) => {
          switch (property) {
            case 'status': return data.status.naziv.toLocaleLowerCase();
            default: return data[property];
          }
        };

        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      });

  }

  public openDialog(flag: number, id?: number, ime?: string, prezime?: string, brojIndeksa?: string,
                    departman?: Departman, status?: Status) {
    const dialogRef = this.dialog.open(StudentDialogComponent, {
      data: {
        i: id, id, ime, prezime, brojIndeksa,
       departman, status
      }
    });
    dialogRef.componentInstance.flag = flag;
    if (flag === 1) {
      dialogRef.componentInstance.data.departman = this.selektovanaDepartman;
    }

    dialogRef.afterClosed().subscribe(result => {
      if (result === 1) {
        this.loadData();
      }
    });
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim();
    filterValue = filterValue.toLocaleLowerCase();
    this.dataSource.filter = filterValue;
  }
}
