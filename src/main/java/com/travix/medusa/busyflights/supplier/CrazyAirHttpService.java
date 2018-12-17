package com.travix.medusa.busyflights.supplier;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.service.SampleCreationUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CrazyAirHttpService {
    public List<CrazyAirResponse> searchFlights(CrazyAirRequest req) {
        int n = ThreadLocalRandom.current().nextInt(0, 10);
        return SampleCreationUtil.createCrazyAirResponses(req, n);
    }
}
