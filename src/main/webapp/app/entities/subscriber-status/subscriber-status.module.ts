import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TfsBackendSharedModule } from 'app/shared/shared.module';
import { SubscriberStatusComponent } from './subscriber-status.component';
import { SubscriberStatusDetailComponent } from './subscriber-status-detail.component';
import { SubscriberStatusUpdateComponent } from './subscriber-status-update.component';
import { SubscriberStatusDeleteDialogComponent } from './subscriber-status-delete-dialog.component';
import { subscriberStatusRoute } from './subscriber-status.route';

@NgModule({
  imports: [TfsBackendSharedModule, RouterModule.forChild(subscriberStatusRoute)],
  declarations: [
    SubscriberStatusComponent,
    SubscriberStatusDetailComponent,
    SubscriberStatusUpdateComponent,
    SubscriberStatusDeleteDialogComponent,
  ],
  entryComponents: [SubscriberStatusDeleteDialogComponent],
})
export class TfsBackendSubscriberStatusModule {}
