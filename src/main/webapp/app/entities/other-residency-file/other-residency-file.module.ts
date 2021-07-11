import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TfsBackendSharedModule } from 'app/shared/shared.module';
import { OtherResidencyFileComponent } from './other-residency-file.component';
import { OtherResidencyFileDetailComponent } from './other-residency-file-detail.component';
import { OtherResidencyFileUpdateComponent } from './other-residency-file-update.component';
import { OtherResidencyFileDeleteDialogComponent } from './other-residency-file-delete-dialog.component';
import { otherResidencyFileRoute } from './other-residency-file.route';

@NgModule({
  imports: [TfsBackendSharedModule, RouterModule.forChild(otherResidencyFileRoute)],
  declarations: [
    OtherResidencyFileComponent,
    OtherResidencyFileDetailComponent,
    OtherResidencyFileUpdateComponent,
    OtherResidencyFileDeleteDialogComponent,
  ],
  entryComponents: [OtherResidencyFileDeleteDialogComponent],
})
export class TfsBackendOtherResidencyFileModule {}
