import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TfsBackendSharedModule } from 'app/shared/shared.module';
import { RequiredDocComponent } from './required-doc.component';
import { RequiredDocDetailComponent } from './required-doc-detail.component';
import { RequiredDocUpdateComponent } from './required-doc-update.component';
import { RequiredDocDeleteDialogComponent } from './required-doc-delete-dialog.component';
import { requiredDocRoute } from './required-doc.route';

@NgModule({
  imports: [TfsBackendSharedModule, RouterModule.forChild(requiredDocRoute)],
  declarations: [RequiredDocComponent, RequiredDocDetailComponent, RequiredDocUpdateComponent, RequiredDocDeleteDialogComponent],
  entryComponents: [RequiredDocDeleteDialogComponent],
})
export class TfsBackendRequiredDocModule {}
