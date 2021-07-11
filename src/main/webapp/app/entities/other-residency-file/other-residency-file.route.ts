import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IOtherResidencyFile, OtherResidencyFile } from 'app/shared/model/other-residency-file.model';
import { OtherResidencyFileService } from './other-residency-file.service';
import { OtherResidencyFileComponent } from './other-residency-file.component';
import { OtherResidencyFileDetailComponent } from './other-residency-file-detail.component';
import { OtherResidencyFileUpdateComponent } from './other-residency-file-update.component';

@Injectable({ providedIn: 'root' })
export class OtherResidencyFileResolve implements Resolve<IOtherResidencyFile> {
  constructor(private service: OtherResidencyFileService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOtherResidencyFile> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((otherResidencyFile: HttpResponse<OtherResidencyFile>) => {
          if (otherResidencyFile.body) {
            return of(otherResidencyFile.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new OtherResidencyFile());
  }
}

export const otherResidencyFileRoute: Routes = [
  {
    path: '',
    component: OtherResidencyFileComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.otherResidencyFile.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: OtherResidencyFileDetailComponent,
    resolve: {
      otherResidencyFile: OtherResidencyFileResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.otherResidencyFile.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: OtherResidencyFileUpdateComponent,
    resolve: {
      otherResidencyFile: OtherResidencyFileResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.otherResidencyFile.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: OtherResidencyFileUpdateComponent,
    resolve: {
      otherResidencyFile: OtherResidencyFileResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.otherResidencyFile.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
