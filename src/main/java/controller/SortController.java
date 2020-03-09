package controller;

import enums.SortEnum;
import execution.SortFactory;
import execution.SortResult;
import items.IntegerItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.util.Collections.EMPTY_LIST;
import static java.util.Objects.isNull;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.util.CollectionUtils.isEmpty;

@RestController
public class SortController {
    @Autowired
    private UserService userService;

    @GetMapping("/noauth/api/sort/enums")
    public ResponseEntity<List<SortEnum>> getEnums() {
        return new ResponseEntity<>(Arrays.asList(SortEnum.values()), OK);
    }

    @GetMapping("/api/sort")
    public ResponseEntity<List<Integer>> generate(@RequestParam(defaultValue = "10") int n,
                                  @RequestParam(required = false) Integer maxValue,
                                  @RequestParam(defaultValue = "true") boolean avoidDuplicates) {
        maxValue = isNull(maxValue) ? n : maxValue;
        return avoidDuplicates && maxValue < n
                ? new ResponseEntity<>(EMPTY_LIST, BAD_REQUEST)
                : new ResponseEntity<>(new IntegerItems(n, maxValue, avoidDuplicates).getItems(), OK);
    }

    @PostMapping("/api/sort")
    public ResponseEntity<SortResult<Integer>> sortValues(@RequestBody(required = false) List<Integer> items,
                                                    @RequestParam(defaultValue = "true") boolean asc,
                                                    @RequestParam(defaultValue = "QUICK") SortEnum sort) {
        if (isEmpty(items)) {
            return new ResponseEntity<>(new SortResult<>(null, null,null, EMPTY_LIST), OK);
        }
        Date bgn = new Date();
        List<Integer> sortedItems = SortFactory.create(new IntegerItems(items), asc, sort).execute();
        return new ResponseEntity<>(new SortResult<>(sort, sortedItems.size(), new Date().getTime() - bgn.getTime(), sortedItems), OK);
    }
}
