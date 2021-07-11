import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISubscriberStatus } from 'app/shared/model/subscriber-status.model';

@Component({
  selector: 'jhi-subscriber-status-detail',
  templateUrl: './subscriber-status-detail.component.html',
})
export class SubscriberStatusDetailComponent implements OnInit {
  subscriberStatus: ISubscriberStatus | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ subscriberStatus }) => (this.subscriberStatus = subscriberStatus));
  }

  previousState(): void {
    window.history.back();
  }
}
