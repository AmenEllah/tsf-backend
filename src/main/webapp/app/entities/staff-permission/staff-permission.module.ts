import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TfsBackendSharedModule } from 'app/shared/shared.module';
import { StaffPermissionComponent } from './staff-permission.component';
import { StaffPermissionDetailComponent } from './staff-permission-detail.component';
import { StaffPermissionUpdateComponent } from './staff-permission-update.component';
import { StaffPermissionDeleteDialogComponent } from './staff-permission-delete-dialog.component';
import { staffPermissionRoute } from './staff-permission.route';

@NgModule({
  imports: [TfsBackendSharedModule, RouterModule.forChild(staffPermissionRoute)],
  declarations: [
    StaffPermissionComponent,
    StaffPermissionDetailComponent,
    StaffPermissionUpdateComponent,
    StaffPermissionDeleteDialogComponent,
  ],
  entryComponents: [StaffPermissionDeleteDialogComponent],
})
export class TfsBackendStaffPermissionModule {}
