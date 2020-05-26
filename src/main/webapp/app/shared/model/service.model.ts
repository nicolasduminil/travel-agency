import { Moment } from 'moment';
import { IDeal } from 'app/shared/model/deal.model';
import { IPack } from 'app/shared/model/pack.model';
import { IAccomodation } from 'app/shared/model/accomodation.model';
import { IActivity } from 'app/shared/model/activity.model';
import { ITransport } from 'app/shared/model/transport.model';

export interface IService {
  id?: number;
  serviceDescription?: string;
  serviceStartDate?: Moment;
  serviceEndDate?: Moment;
  servicePrice?: number;
  deals?: IDeal[];
  packages?: IPack[];
  accomodations?: IAccomodation[];
  activities?: IActivity[];
  transports?: ITransport[];
}

export class Service implements IService {
  constructor(
    public id?: number,
    public serviceDescription?: string,
    public serviceStartDate?: Moment,
    public serviceEndDate?: Moment,
    public servicePrice?: number,
    public deals?: IDeal[],
    public packages?: IPack[],
    public accomodations?: IAccomodation[],
    public activities?: IActivity[],
    public transports?: ITransport[]
  ) {}
}
