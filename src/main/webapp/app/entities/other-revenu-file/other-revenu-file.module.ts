import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TfsBackendSharedModule } from 'app/shared/shared.module';
import { OtherRevenuFileComponent } from './other-revenu-file.component';
import { OtherRevenuFileDetailComponent } from './other-revenu-file-detail.component';
import { OtherRevenuFileUpdateComponent } from './other-revenu-file-update.component';
import { OtherRevenuFileDeleteDialogComponent } from './other-revenu-file-delete-dialog.component';
import { otherRevenuFileRoute } from './other-revenu-file.route';

@NgModule({
  imports: [TfsBackendSharedModule, RouterModule.forChild(otherRevenuFileRoute)],
  declarations: [
    OtherRevenuFileComponent,
    OtherRevenuFileDetailComponent,
    OtherRevenuFileUpdateComponent,
    OtherRevenuFileDeleteDialogComponent,
  ],
  entryComponents: [OtherRevenuFileDeleteDialogComponent],
})
export class TfsBackendOtherRevenuFileModule {}
