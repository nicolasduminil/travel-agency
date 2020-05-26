import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IService } from 'app/shared/model/service.model';

type EntityResponseType = HttpResponse<IService>;
type EntityArrayResponseType = HttpResponse<IService[]>;

@Injectable({ providedIn: 'root' })
export class ServiceService {
  public resourceUrl = SERVER_API_URL + 'api/services';

  constructor(protected http: HttpClient) {}

  create(service: IService): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(service);
    return this.http
      .post<IService>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(service: IService): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(service);
    return this.http
      .put<IService>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IService>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IService[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(service: IService): IService {
    const copy: IService = Object.assign({}, service, {
      serviceStartDate:
        service.serviceStartDate && service.serviceStartDate.isValid() ? service.serviceStartDate.format(DATE_FORMAT) : undefined,
      serviceEndDate: service.serviceEndDate && service.serviceEndDate.isValid() ? service.serviceEndDate.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.serviceStartDate = res.body.serviceStartDate ? moment(res.body.serviceStartDate) : undefined;
      res.body.serviceEndDate = res.body.serviceEndDate ? moment(res.body.serviceEndDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((service: IService) => {
        service.serviceStartDate = service.serviceStartDate ? moment(service.serviceStartDate) : undefined;
        service.serviceEndDate = service.serviceEndDate ? moment(service.serviceEndDate) : undefined;
      });
    }
    return res;
  }
}
