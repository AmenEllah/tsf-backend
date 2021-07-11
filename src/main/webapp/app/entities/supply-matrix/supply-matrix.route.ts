import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISupplyMatrix, SupplyMatrix } from 'app/shared/model/supply-matrix.model';
import { SupplyMatrixService } from './supply-matrix.service';
import { SupplyMatrixComponent } from './supply-matrix.component';
import { SupplyMatrixDetailComponent } from './supply-matrix-detail.component';
import { SupplyMatrixUpdateComponent } from './supply-matrix-update.component';

@Injectable({ providedIn: 'root' })
export class SupplyMatrixResolve implements Resolve<ISupplyMatrix> {
  constructor(private service: SupplyMatrixService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISupplyMatrix> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((supplyMatrix: HttpResponse<SupplyMatrix>) => {
          if (supplyMatrix.body) {
            return of(supplyMatrix.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SupplyMatrix());
  }
}

export const supplyMatrixRoute: Routes = [
  {
    path: '',
    component: SupplyMatrixComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'tfsBackendApp.supplyMatrix.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SupplyMatrixDetailComponent,
    resolve: {
      supplyMatrix: SupplyMatrixResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.supplyMatrix.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SupplyMatrixUpdateComponent,
    resolve: {
      supplyMatrix: SupplyMatrixResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.supplyMatrix.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SupplyMatrixUpdateComponent,
    resolve: {
      supplyMatrix: SupplyMatrixResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.supplyMatrix.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
