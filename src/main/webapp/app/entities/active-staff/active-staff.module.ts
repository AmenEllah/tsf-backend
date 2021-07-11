import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TfsBackendSharedModule } from 'app/shared/shared.module';
import { ActiveStaffComponent } from './active-staff.component';
import { ActiveStaffDetailComponent } from './active-staff-detail.component';
import { ActiveStaffUpdateComponent } from './active-staff-update.component';
import { ActiveStaffDeleteDialogComponent } from './active-staff-delete-dialog.component';
import { activeStaffRoute } from './active-staff.route';

@NgModule({
  imports: [TfsBackendSharedModule, RouterModule.forChild(activeStaffRoute)],
  declarations: [ActiveStaffComponent, ActiveStaffDetailComponent, ActiveStaffUpdateComponent, ActiveStaffDeleteDialogComponent],
  entryComponents: [ActiveStaffDeleteDialogComponent],
})
export class TfsBackendActiveStaffModule {}
