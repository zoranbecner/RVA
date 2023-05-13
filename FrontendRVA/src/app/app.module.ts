import { AppRoutingModule } from './app-routing.module';
import { DepartmanService } from './services/departman.service';
import { StatusService } from 'src/app/services/status.service';
import { VoziloComponent } from './components/primer-components/vozilo/vozilo.component';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatTableModule } from '@angular/material/table';
import { MatInputModule } from '@angular/material/input';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { HttpClientModule } from '@angular/common/http';
import { MatSelectModule } from '@angular/material/select';
import { MatDialogModule } from '@angular/material/dialog';
import { MatNativeDateModule } from '@angular/material/core';
import { MatSnackBarModule } from '@angular/material/snack-bar';

import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { AutomobilComponent } from './components/primer-components/automobil/automobil.component';
import { HomeComponent } from './components/core/home/home.component';
import { AuthorComponent } from './components/core/author/author.component';
import { AboutComponent } from './components/core/about/about.component';
import { StatusComponent } from './components/status/status.component';
import { FakultetComponent } from './components/fakultet/fakultet.component';
import { DepartmanComponent } from './components/departman/departman.component';
import { StudentComponent } from './components/student/student.component';
import { FakultetService } from './services/fakultet.service';
import { StudentService } from './services/student.service';
import { StatusDialogComponent } from './components/dialogs/status-dialog/status-dialog.component';
import { FakultetDialogComponent } from './components/dialogs/fakultet-dialog/fakultet-dialog.component';
import { DepartmanDialogComponent } from './components/dialogs/departman-dialog/departman-dialog.component';
import { StudentDialogComponent } from './components/dialogs/student-dialog/student-dialog.component';


@NgModule({
  declarations: [
    AppComponent,
    VoziloComponent,
    AutomobilComponent,
    HomeComponent,
    AuthorComponent,
    AboutComponent,
    StatusComponent,
    FakultetComponent,
    DepartmanComponent,
    StudentComponent,
    StatusDialogComponent,
    FakultetDialogComponent,
    DepartmanDialogComponent,
    StudentDialogComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatIconModule,
    MatSidenavModule,
    MatListModule,
    MatGridListModule,
    MatExpansionModule,
    MatToolbarModule,
    MatNativeDateModule,
    MatFormFieldModule,
    MatInputModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatSnackBarModule,
    MatCheckboxModule,
    MatDialogModule,
    MatDatepickerModule,
    MatSelectModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule
  ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
