import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IRequiredDocIncome, RequiredDocIncome } from 'app/shared/model/required-doc-income.model';
import { RequiredDocIncomeService } from './required-doc-income.service';
import { RequiredDocIncomeComponent } from './required-doc-income.component';
import { RequiredDocIncomeDetailComponent } from './required-doc-income-detail.component';
import { RequiredDocIncomeUpdateComponent } from './required-doc-income-update.component';

@Injectable({ providedIn: 'root' })
export class RequiredDocIncomeResolve implements Resolve<IRequiredDocIncome> {
  constructor(private service: RequiredDocIncomeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRequiredDocIncome> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((requiredDocIncome: HttpResponse<RequiredDocIncome>) => {
          if (requiredDocIncome.body) {
            return of(requiredDocIncome.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new RequiredDocIncome());
  }
}

export const requiredDocIncomeRoute: Routes = [
  {
    path: '',
    component: RequiredDocIncomeComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.requiredDocIncome.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RequiredDocIncomeDetailComponent,
    resolve: {
      requiredDocIncome: RequiredDocIncomeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.requiredDocIncome.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RequiredDocIncomeUpdateComponent,
    resolve: {
      requiredDocIncome: RequiredDocIncomeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.requiredDocIncome.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RequiredDocIncomeUpdateComponent,
    resolve: {
      requiredDocIncome: RequiredDocIncomeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.requiredDocIncome.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
