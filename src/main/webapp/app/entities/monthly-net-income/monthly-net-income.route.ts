import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMonthlyNetIncome, MonthlyNetIncome } from 'app/shared/model/monthly-net-income.model';
import { MonthlyNetIncomeService } from './monthly-net-income.service';
import { MonthlyNetIncomeComponent } from './monthly-net-income.component';
import { MonthlyNetIncomeDetailComponent } from './monthly-net-income-detail.component';
import { MonthlyNetIncomeUpdateComponent } from './monthly-net-income-update.component';

@Injectable({ providedIn: 'root' })
export class MonthlyNetIncomeResolve implements Resolve<IMonthlyNetIncome> {
  constructor(private service: MonthlyNetIncomeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMonthlyNetIncome> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((monthlyNetIncome: HttpResponse<MonthlyNetIncome>) => {
          if (monthlyNetIncome.body) {
            return of(monthlyNetIncome.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new MonthlyNetIncome());
  }
}

export const monthlyNetIncomeRoute: Routes = [
  {
    path: '',
    component: MonthlyNetIncomeComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'tfsBackendApp.monthlyNetIncome.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: MonthlyNetIncomeDetailComponent,
    resolve: {
      monthlyNetIncome: MonthlyNetIncomeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.monthlyNetIncome.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: MonthlyNetIncomeUpdateComponent,
    resolve: {
      monthlyNetIncome: MonthlyNetIncomeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.monthlyNetIncome.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: MonthlyNetIncomeUpdateComponent,
    resolve: {
      monthlyNetIncome: MonthlyNetIncomeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.monthlyNetIncome.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
