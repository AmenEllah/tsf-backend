import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TfsBackendSharedModule } from 'app/shared/shared.module';
import { PersonalInfoComponent } from './personal-info.component';
import { PersonalInfoDetailComponent } from './personal-info-detail.component';
import { PersonalInfoUpdateComponent } from './personal-info-update.component';
import { PersonalInfoDeleteDialogComponent } from './personal-info-delete-dialog.component';
import { personalInfoRoute } from './personal-info.route';

@NgModule({
  imports: [TfsBackendSharedModule, RouterModule.forChild(personalInfoRoute)],
  declarations: [PersonalInfoComponent, PersonalInfoDetailComponent, PersonalInfoUpdateComponent, PersonalInfoDeleteDialogComponent],
  entryComponents: [PersonalInfoDeleteDialogComponent],
})
export class TfsBackendPersonalInfoModule {}
