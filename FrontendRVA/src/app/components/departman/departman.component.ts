import { Fakultet } from './../../models/fakultet';
import { MatSort } from '@angular/material/sort';
import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Departman } from 'src/app/models/departman';
import { MatPaginator } from '@angular/material/paginator';
import { DepartmanService } from 'src/app/services/departman.service';
import { MatDialog } from '@angular/material/dialog';
import { DepartmanDialogComponent } from '../dialogs/departman-dialog/departman-dialog.component';

@Component({
  selector: 'app-departman',
  templateUrl: './departman.component.html',
  styleUrls: ['./departman.component.css']
})
export class DepartmanComponent implements OnInit {

  displayedColumns = ['id', 'naziv', 'oznaka', 'fakultet',  'actions'];
  dataSource: MatTableDataSource<Departman>;
  selektovanaDepartman: Departman;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(public departmanService: DepartmanService,
              public dialog: MatDialog) { }

  ngOnInit() {
    this.loadData();
  }

  public loadData() {
    this.departmanService.getAllDepartman().subscribe(data => {
      this.dataSource = new MatTableDataSource(data);

      // pretraga po nazivu ugnježdenog objekta
      this.dataSource.filterPredicate = (data, filter: string) => {
        const accumulator = (currentTerm, key) => {
          return key === 'fakultet' ? currentTerm + data.fakultet.naziv : currentTerm + data[key];
        };
        const dataStr = Object.keys(data).reduce(accumulator, '').toLowerCase();
        const transformedFilter = filter.trim().toLowerCase();
        return dataStr.indexOf(transformedFilter) !== -1;
      };

      // sortiranje po nazivu ugnježdenog objekta
      this.dataSource.sortingDataAccessor = (data, property) => {
        switch (property) {
          case 'fakultet': return data.fakultet.naziv.toLocaleLowerCase();
          default: return data[property];
        }
      };

      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });

  }

  public openDialog(flag: number, id?: number,  naziv?: string, oznaka?: string, fakultet?: Fakultet ) {
    const dialogRef = this.dialog.open(DepartmanDialogComponent,
       { data: { id, naziv, oznaka, fakultet  } });
    dialogRef.componentInstance.flag = flag;

    dialogRef.afterClosed().subscribe(result => {
      if (result === 1){
         this.loadData();
      }

    });
  }

  selectRow(row: any) {
    this.selektovanaDepartman = row;
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim();
    filterValue = filterValue.toLocaleLowerCase();
    this.dataSource.filter = filterValue;
  }
}
