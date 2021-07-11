import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TfsBackendSharedModule } from 'app/shared/shared.module';
import { MunicipalityComponent } from './municipality.component';
import { MunicipalityDetailComponent } from './municipality-detail.component';
import { MunicipalityUpdateComponent } from './municipality-update.component';
import { MunicipalityDeleteDialogComponent } from './municipality-delete-dialog.component';
import { municipalityRoute } from './municipality.route';

@NgModule({
  imports: [TfsBackendSharedModule, RouterModule.forChild(municipalityRoute)],
  declarations: [MunicipalityComponent, MunicipalityDetailComponent, MunicipalityUpdateComponent, MunicipalityDeleteDialogComponent],
  entryComponents: [MunicipalityDeleteDialogComponent],
})
export class TfsBackendMunicipalityModule {}
