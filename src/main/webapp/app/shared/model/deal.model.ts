import { Moment } from 'moment';
import { IContact } from 'app/shared/model/contact.model';
import { ICustomer } from 'app/shared/model/customer.model';
import { IService } from 'app/shared/model/service.model';

export interface IDeal {
  id?: number;
  dealName?: string;
  dealBookDate?: Moment;
  agents?: IContact[];
  customers?: ICustomer[];
  services?: IService[];
}

export class Deal implements IDeal {
  constructor(
    public id?: number,
    public dealName?: string,
    public dealBookDate?: Moment,
    public agents?: IContact[],
    public customers?: ICustomer[],
    public services?: IService[]
  ) {}
}
