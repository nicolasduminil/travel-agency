import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TravelAgencySharedModule } from 'app/shared/shared.module';
import { TransportComponent } from './transport.component';
import { TransportDetailComponent } from './transport-detail.component';
import { TransportUpdateComponent } from './transport-update.component';
import { TransportDeleteDialogComponent } from './transport-delete-dialog.component';
import { transportRoute } from './transport.route';

@NgModule({
  imports: [TravelAgencySharedModule, RouterModule.forChild(transportRoute)],
  declarations: [TransportComponent, TransportDetailComponent, TransportUpdateComponent, TransportDeleteDialogComponent],
  entryComponents: [TransportDeleteDialogComponent],
})
export class TravelAgencyTransportModule {}
