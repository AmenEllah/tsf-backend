import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TfsBackendSharedModule } from 'app/shared/shared.module';
import { ReqDocUploadComponent } from './req-doc-upload.component';
import { ReqDocUploadDetailComponent } from './req-doc-upload-detail.component';
import { ReqDocUploadUpdateComponent } from './req-doc-upload-update.component';
import { ReqDocUploadDeleteDialogComponent } from './req-doc-upload-delete-dialog.component';
import { reqDocUploadRoute } from './req-doc-upload.route';

@NgModule({
  imports: [TfsBackendSharedModule, RouterModule.forChild(reqDocUploadRoute)],
  declarations: [ReqDocUploadComponent, ReqDocUploadDetailComponent, ReqDocUploadUpdateComponent, ReqDocUploadDeleteDialogComponent],
  entryComponents: [ReqDocUploadDeleteDialogComponent],
})
export class TfsBackendReqDocUploadModule {}
