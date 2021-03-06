import { IService } from 'app/shared/model/service.model';
import { AccomodationType } from 'app/shared/model/enumerations/accomodation-type.model';
import { AccomodationClass } from 'app/shared/model/enumerations/accomodation-class.model';

export interface IAccomodation {
  id?: number;
  accomodationName?: string;
  accomodationType?: AccomodationType;
  accomodationClass?: AccomodationClass;
  locationId?: number;
  services?: IService[];
}

export class Accomodation implements IAccomodation {
  constructor(
    public id?: number,
    public accomodationName?: string,
    public accomodationType?: AccomodationType,
    public accomodationClass?: AccomodationClass,
    public locationId?: number,
    public services?: IService[]
  ) {}
}
