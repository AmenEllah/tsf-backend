import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBotScan } from 'app/shared/model/bot-scan.model';

@Component({
  selector: 'jhi-bot-scan-detail',
  templateUrl: './bot-scan-detail.component.html',
})
export class BotScanDetailComponent implements OnInit {
  botScan: IBotScan | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ botScan }) => (this.botScan = botScan));
  }

  previousState(): void {
    window.history.back();
  }
}
