import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TravelAgencySharedModule } from 'app/shared/shared.module';
import { DealComponent } from './deal.component';
import { DealDetailComponent } from './deal-detail.component';
import { DealUpdateComponent } from './deal-update.component';
import { DealDeleteDialogComponent } from './deal-delete-dialog.component';
import { dealRoute } from './deal.route';

@NgModule({
  imports: [TravelAgencySharedModule, RouterModule.forChild(dealRoute)],
  declarations: [DealComponent, DealDetailComponent, DealUpdateComponent, DealDeleteDialogComponent],
  entryComponents: [DealDeleteDialogComponent],
})
export class TravelAgencyDealModule {}
