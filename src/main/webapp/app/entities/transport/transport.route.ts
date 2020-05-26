import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITransport, Transport } from 'app/shared/model/transport.model';
import { TransportService } from './transport.service';
import { TransportComponent } from './transport.component';
import { TransportDetailComponent } from './transport-detail.component';
import { TransportUpdateComponent } from './transport-update.component';

@Injectable({ providedIn: 'root' })
export class TransportResolve implements Resolve<ITransport> {
  constructor(private service: TransportService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITransport> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((transport: HttpResponse<Transport>) => {
          if (transport.body) {
            return of(transport.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Transport());
  }
}

export const transportRoute: Routes = [
  {
    path: '',
    component: TransportComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'travelAgencyApp.transport.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TransportDetailComponent,
    resolve: {
      transport: TransportResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'travelAgencyApp.transport.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TransportUpdateComponent,
    resolve: {
      transport: TransportResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'travelAgencyApp.transport.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TransportUpdateComponent,
    resolve: {
      transport: TransportResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'travelAgencyApp.transport.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
