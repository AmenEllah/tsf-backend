import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TfsBackendSharedModule } from 'app/shared/shared.module';
import { AdressInfoComponent } from './adress-info.component';
import { AdressInfoDetailComponent } from './adress-info-detail.component';
import { AdressInfoUpdateComponent } from './adress-info-update.component';
import { AdressInfoDeleteDialogComponent } from './adress-info-delete-dialog.component';
import { adressInfoRoute } from './adress-info.route';

@NgModule({
  imports: [TfsBackendSharedModule, RouterModule.forChild(adressInfoRoute)],
  declarations: [AdressInfoComponent, AdressInfoDetailComponent, AdressInfoUpdateComponent, AdressInfoDeleteDialogComponent],
  entryComponents: [AdressInfoDeleteDialogComponent],
})
export class TfsBackendAdressInfoModule {}
