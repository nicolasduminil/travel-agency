import { IContact } from 'app/shared/model/contact.model';
import { IService } from 'app/shared/model/service.model';
import { ActivityType } from 'app/shared/model/enumerations/activity-type.model';

export interface IActivity {
  id?: number;
  activityDescription?: string;
  activityType?: ActivityType;
  locationId?: number;
  contacts?: IContact[];
  services?: IService[];
}

export class Activity implements IActivity {
  constructor(
    public id?: number,
    public activityDescription?: string,
    public activityType?: ActivityType,
    public locationId?: number,
    public contacts?: IContact[],
    public services?: IService[]
  ) {}
}
