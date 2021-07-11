import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IOtherRevenuFile, OtherRevenuFile } from 'app/shared/model/other-revenu-file.model';
import { OtherRevenuFileService } from './other-revenu-file.service';
import { OtherRevenuFileComponent } from './other-revenu-file.component';
import { OtherRevenuFileDetailComponent } from './other-revenu-file-detail.component';
import { OtherRevenuFileUpdateComponent } from './other-revenu-file-update.component';

@Injectable({ providedIn: 'root' })
export class OtherRevenuFileResolve implements Resolve<IOtherRevenuFile> {
  constructor(private service: OtherRevenuFileService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOtherRevenuFile> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((otherRevenuFile: HttpResponse<OtherRevenuFile>) => {
          if (otherRevenuFile.body) {
            return of(otherRevenuFile.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new OtherRevenuFile());
  }
}

export const otherRevenuFileRoute: Routes = [
  {
    path: '',
    component: OtherRevenuFileComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.otherRevenuFile.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: OtherRevenuFileDetailComponent,
    resolve: {
      otherRevenuFile: OtherRevenuFileResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.otherRevenuFile.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: OtherRevenuFileUpdateComponent,
    resolve: {
      otherRevenuFile: OtherRevenuFileResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.otherRevenuFile.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: OtherRevenuFileUpdateComponent,
    resolve: {
      otherRevenuFile: OtherRevenuFileResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.otherRevenuFile.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
