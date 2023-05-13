import { Status } from './status';
import { Departman } from './departman';

export class Student{
  id: number;
  ime: string;
  prezime: string;
  brojIndeksa: string;
  status: Status;
  departman: Departman;
}
