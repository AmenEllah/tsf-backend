import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TfsBackendSharedModule } from 'app/shared/shared.module';
import { RequiredDocResidencyComponent } from './required-doc-residency.component';
import { RequiredDocResidencyDetailComponent } from './required-doc-residency-detail.component';
import { RequiredDocResidencyUpdateComponent } from './required-doc-residency-update.component';
import { RequiredDocResidencyDeleteDialogComponent } from './required-doc-residency-delete-dialog.component';
import { requiredDocResidencyRoute } from './required-doc-residency.route';

@NgModule({
  imports: [TfsBackendSharedModule, RouterModule.forChild(requiredDocResidencyRoute)],
  declarations: [
    RequiredDocResidencyComponent,
    RequiredDocResidencyDetailComponent,
    RequiredDocResidencyUpdateComponent,
    RequiredDocResidencyDeleteDialogComponent,
  ],
  entryComponents: [RequiredDocResidencyDeleteDialogComponent],
})
export class TfsBackendRequiredDocResidencyModule {}
