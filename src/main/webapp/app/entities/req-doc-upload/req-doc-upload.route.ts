import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IReqDocUpload, ReqDocUpload } from 'app/shared/model/req-doc-upload.model';
import { ReqDocUploadService } from './req-doc-upload.service';
import { ReqDocUploadComponent } from './req-doc-upload.component';
import { ReqDocUploadDetailComponent } from './req-doc-upload-detail.component';
import { ReqDocUploadUpdateComponent } from './req-doc-upload-update.component';

@Injectable({ providedIn: 'root' })
export class ReqDocUploadResolve implements Resolve<IReqDocUpload> {
  constructor(private service: ReqDocUploadService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IReqDocUpload> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((reqDocUpload: HttpResponse<ReqDocUpload>) => {
          if (reqDocUpload.body) {
            return of(reqDocUpload.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ReqDocUpload());
  }
}

export const reqDocUploadRoute: Routes = [
  {
    path: '',
    component: ReqDocUploadComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'tfsBackendApp.reqDocUpload.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ReqDocUploadDetailComponent,
    resolve: {
      reqDocUpload: ReqDocUploadResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.reqDocUpload.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ReqDocUploadUpdateComponent,
    resolve: {
      reqDocUpload: ReqDocUploadResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.reqDocUpload.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ReqDocUploadUpdateComponent,
    resolve: {
      reqDocUpload: ReqDocUploadResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.reqDocUpload.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
