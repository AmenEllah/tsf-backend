import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TfsBackendSharedModule } from 'app/shared/shared.module';
import { ResidencyDocumentComponent } from './residency-document.component';
import { ResidencyDocumentDetailComponent } from './residency-document-detail.component';
import { ResidencyDocumentUpdateComponent } from './residency-document-update.component';
import { ResidencyDocumentDeleteDialogComponent } from './residency-document-delete-dialog.component';
import { residencyDocumentRoute } from './residency-document.route';

@NgModule({
  imports: [TfsBackendSharedModule, RouterModule.forChild(residencyDocumentRoute)],
  declarations: [
    ResidencyDocumentComponent,
    ResidencyDocumentDetailComponent,
    ResidencyDocumentUpdateComponent,
    ResidencyDocumentDeleteDialogComponent,
  ],
  entryComponents: [ResidencyDocumentDeleteDialogComponent],
})
export class TfsBackendResidencyDocumentModule {}
