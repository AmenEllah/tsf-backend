import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TfsBackendSharedModule } from 'app/shared/shared.module';
import { SubcontractingStaffComponent } from './subcontracting-staff.component';
import { SubcontractingStaffDetailComponent } from './subcontracting-staff-detail.component';
import { SubcontractingStaffUpdateComponent } from './subcontracting-staff-update.component';
import { SubcontractingStaffDeleteDialogComponent } from './subcontracting-staff-delete-dialog.component';
import { subcontractingStaffRoute } from './subcontracting-staff.route';

@NgModule({
  imports: [TfsBackendSharedModule, RouterModule.forChild(subcontractingStaffRoute)],
  declarations: [
    SubcontractingStaffComponent,
    SubcontractingStaffDetailComponent,
    SubcontractingStaffUpdateComponent,
    SubcontractingStaffDeleteDialogComponent,
  ],
  entryComponents: [SubcontractingStaffDeleteDialogComponent],
})
export class TfsBackendSubcontractingStaffModule {}
