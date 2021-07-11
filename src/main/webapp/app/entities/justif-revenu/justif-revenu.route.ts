import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IJustifRevenu, JustifRevenu } from 'app/shared/model/justif-revenu.model';
import { JustifRevenuService } from './justif-revenu.service';
import { JustifRevenuComponent } from './justif-revenu.component';
import { JustifRevenuDetailComponent } from './justif-revenu-detail.component';
import { JustifRevenuUpdateComponent } from './justif-revenu-update.component';

@Injectable({ providedIn: 'root' })
export class JustifRevenuResolve implements Resolve<IJustifRevenu> {
  constructor(private service: JustifRevenuService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IJustifRevenu> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((justifRevenu: HttpResponse<JustifRevenu>) => {
          if (justifRevenu.body) {
            return of(justifRevenu.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new JustifRevenu());
  }
}

export const justifRevenuRoute: Routes = [
  {
    path: '',
    component: JustifRevenuComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'tfsBackendApp.justifRevenu.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: JustifRevenuDetailComponent,
    resolve: {
      justifRevenu: JustifRevenuResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.justifRevenu.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: JustifRevenuUpdateComponent,
    resolve: {
      justifRevenu: JustifRevenuResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.justifRevenu.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: JustifRevenuUpdateComponent,
    resolve: {
      justifRevenu: JustifRevenuResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.justifRevenu.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
