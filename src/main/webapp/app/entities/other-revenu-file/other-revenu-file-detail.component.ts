import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOtherRevenuFile } from 'app/shared/model/other-revenu-file.model';

@Component({
  selector: 'jhi-other-revenu-file-detail',
  templateUrl: './other-revenu-file-detail.component.html',
})
export class OtherRevenuFileDetailComponent implements OnInit {
  otherRevenuFile: IOtherRevenuFile | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ otherRevenuFile }) => (this.otherRevenuFile = otherRevenuFile));
  }

  previousState(): void {
    window.history.back();
  }
}
