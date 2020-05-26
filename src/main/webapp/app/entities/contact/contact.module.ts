import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TravelAgencySharedModule } from 'app/shared/shared.module';
import { ContactComponent } from './contact.component';
import { ContactDetailComponent } from './contact-detail.component';
import { ContactUpdateComponent } from './contact-update.component';
import { ContactDeleteDialogComponent } from './contact-delete-dialog.component';
import { contactRoute } from './contact.route';

@NgModule({
  imports: [TravelAgencySharedModule, RouterModule.forChild(contactRoute)],
  declarations: [ContactComponent, ContactDetailComponent, ContactUpdateComponent, ContactDeleteDialogComponent],
  entryComponents: [ContactDeleteDialogComponent],
})
export class TravelAgencyContactModule {}
