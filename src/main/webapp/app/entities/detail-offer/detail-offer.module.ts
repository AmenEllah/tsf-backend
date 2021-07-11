import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TfsBackendSharedModule } from 'app/shared/shared.module';
import { DetailOfferComponent } from './detail-offer.component';
import { DetailOfferDetailComponent } from './detail-offer-detail.component';
import { DetailOfferUpdateComponent } from './detail-offer-update.component';
import { DetailOfferDeleteDialogComponent } from './detail-offer-delete-dialog.component';
import { detailOfferRoute } from './detail-offer.route';

@NgModule({
  imports: [TfsBackendSharedModule, RouterModule.forChild(detailOfferRoute)],
  declarations: [DetailOfferComponent, DetailOfferDetailComponent, DetailOfferUpdateComponent, DetailOfferDeleteDialogComponent],
  entryComponents: [DetailOfferDeleteDialogComponent],
})
export class TfsBackendDetailOfferModule {}
