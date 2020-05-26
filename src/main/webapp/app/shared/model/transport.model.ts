import { IService } from 'app/shared/model/service.model';
import { TransportType } from 'app/shared/model/enumerations/transport-type.model';

export interface ITransport {
  id?: number;
  transportType?: TransportType;
  transportName?: string;
  transportDescription?: string;
  toId?: number;
  fromId?: number;
  services?: IService[];
}

export class Transport implements ITransport {
  constructor(
    public id?: number,
    public transportType?: TransportType,
    public transportName?: string,
    public transportDescription?: string,
    public toId?: number,
    public fromId?: number,
    public services?: IService[]
  ) {}
}
