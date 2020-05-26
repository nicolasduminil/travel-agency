import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'accomodation',
        loadChildren: () => import('./accomodation/accomodation.module').then(m => m.TravelAgencyAccomodationModule),
      },
      {
        path: 'activity',
        loadChildren: () => import('./activity/activity.module').then(m => m.TravelAgencyActivityModule),
      },
      {
        path: 'contact',
        loadChildren: () => import('./contact/contact.module').then(m => m.TravelAgencyContactModule),
      },
      {
        path: 'customer',
        loadChildren: () => import('./customer/customer.module').then(m => m.TravelAgencyCustomerModule),
      },
      {
        path: 'location',
        loadChildren: () => import('./location/location.module').then(m => m.TravelAgencyLocationModule),
      },
      {
        path: 'service',
        loadChildren: () => import('./service/service.module').then(m => m.TravelAgencyServiceModule),
      },
      {
        path: 'deal',
        loadChildren: () => import('./deal/deal.module').then(m => m.TravelAgencyDealModule),
      },
      {
        path: 'pack',
        loadChildren: () => import('./pack/pack.module').then(m => m.TravelAgencyPackModule),
      },
      {
        path: 'transport',
        loadChildren: () => import('./transport/transport.module').then(m => m.TravelAgencyTransportModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class TravelAgencyEntityModule {}
