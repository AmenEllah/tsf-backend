import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TfsBackendSharedModule } from 'app/shared/shared.module';
import { SupplyMatrixComponent } from './supply-matrix.component';
import { SupplyMatrixDetailComponent } from './supply-matrix-detail.component';
import { SupplyMatrixUpdateComponent } from './supply-matrix-update.component';
import { SupplyMatrixDeleteDialogComponent } from './supply-matrix-delete-dialog.component';
import { supplyMatrixRoute } from './supply-matrix.route';

@NgModule({
  imports: [TfsBackendSharedModule, RouterModule.forChild(supplyMatrixRoute)],
  declarations: [SupplyMatrixComponent, SupplyMatrixDetailComponent, SupplyMatrixUpdateComponent, SupplyMatrixDeleteDialogComponent],
  entryComponents: [SupplyMatrixDeleteDialogComponent],
})
export class TfsBackendSupplyMatrixModule {}
