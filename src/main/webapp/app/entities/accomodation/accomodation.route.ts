import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAccomodation, Accomodation } from 'app/shared/model/accomodation.model';
import { AccomodationService } from './accomodation.service';
import { AccomodationComponent } from './accomodation.component';
import { AccomodationDetailComponent } from './accomodation-detail.component';
import { AccomodationUpdateComponent } from './accomodation-update.component';

@Injectable({ providedIn: 'root' })
export class AccomodationResolve implements Resolve<IAccomodation> {
  constructor(private service: AccomodationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAccomodation> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((accomodation: HttpResponse<Accomodation>) => {
          if (accomodation.body) {
            return of(accomodation.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Accomodation());
  }
}

export const accomodationRoute: Routes = [
  {
    path: '',
    component: AccomodationComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'travelAgencyApp.accomodation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AccomodationDetailComponent,
    resolve: {
      accomodation: AccomodationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'travelAgencyApp.accomodation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AccomodationUpdateComponent,
    resolve: {
      accomodation: AccomodationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'travelAgencyApp.accomodation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AccomodationUpdateComponent,
    resolve: {
      accomodation: AccomodationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'travelAgencyApp.accomodation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
