import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOtherResidencyFile } from 'app/shared/model/other-residency-file.model';

@Component({
  selector: 'jhi-other-residency-file-detail',
  templateUrl: './other-residency-file-detail.component.html',
})
export class OtherResidencyFileDetailComponent implements OnInit {
  otherResidencyFile: IOtherResidencyFile | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ otherResidencyFile }) => (this.otherResidencyFile = otherResidencyFile));
  }

  previousState(): void {
    window.history.back();
  }
}
