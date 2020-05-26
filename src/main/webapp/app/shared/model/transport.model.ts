import { ILocation } from 'app/shared/model/location.model';
import { IService } from 'app/shared/model/service.model';
import { TransportType } from 'app/shared/model/enumerations/transport-type.model';

export interface ITransport {
  id?: number;
  transportType?: TransportType;
  transportName?: string;
  transportDescription?: string;
  to?: ILocation;
  from?: ILocation;
  services?: IService[];
}

export class Transport implements ITransport {
  constructor(
    public id?: number,
    public transportType?: TransportType,
    public transportName?: string,
    public transportDescription?: string,
    public to?: ILocation,
    public from?: ILocation,
    public services?: IService[]
  ) {}
}
