import { TestBed } from '@angular/core/testing';

import { MobilityServices } from './mobility.service';

describe('MobilityServicesService', () => {
  let service: MobilityServices;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MobilityServices);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
