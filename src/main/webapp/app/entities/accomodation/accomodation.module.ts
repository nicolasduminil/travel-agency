import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TravelAgencySharedModule } from 'app/shared/shared.module';
import { AccomodationComponent } from './accomodation.component';
import { AccomodationDetailComponent } from './accomodation-detail.component';
import { AccomodationUpdateComponent } from './accomodation-update.component';
import { AccomodationDeleteDialogComponent } from './accomodation-delete-dialog.component';
import { accomodationRoute } from './accomodation.route';

@NgModule({
  imports: [TravelAgencySharedModule, RouterModule.forChild(accomodationRoute)],
  declarations: [AccomodationComponent, AccomodationDetailComponent, AccomodationUpdateComponent, AccomodationDeleteDialogComponent],
  entryComponents: [AccomodationDeleteDialogComponent],
})
export class TravelAgencyAccomodationModule {}
