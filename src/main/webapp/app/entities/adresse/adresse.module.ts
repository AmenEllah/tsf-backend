import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TfsBackendSharedModule } from 'app/shared/shared.module';
import { AdresseComponent } from './adresse.component';
import { AdresseDetailComponent } from './adresse-detail.component';
import { AdresseUpdateComponent } from './adresse-update.component';
import { AdresseDeleteDialogComponent } from './adresse-delete-dialog.component';
import { adresseRoute } from './adresse.route';

@NgModule({
  imports: [TfsBackendSharedModule, RouterModule.forChild(adresseRoute)],
  declarations: [AdresseComponent, AdresseDetailComponent, AdresseUpdateComponent, AdresseDeleteDialogComponent],
  entryComponents: [AdresseDeleteDialogComponent],
})
export class TfsBackendAdresseModule {}
