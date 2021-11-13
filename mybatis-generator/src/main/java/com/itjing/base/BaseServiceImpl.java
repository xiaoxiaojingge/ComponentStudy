package com.itjing.base;

import com.itjing.utils.SpringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author lijing
 * @date 2021年11月13日 8:28
 * @description 实现 BaseService 抽象类
 */
public abstract class BaseServiceImpl<Mapper, Record, Example> implements BaseService<Record, Example> {

    public Mapper mapper;

    @Override
    public int countByExample(Example example) {
        try {
            Method countByExample = mapper.getClass().getDeclaredMethod("countByExample", example.getClass());
            Object result = countByExample.invoke(mapper, example);
            return Integer.parseInt(String.valueOf(result));
        } catch (Exception e) {
            throw new RuntimeException("[countByExample]错误", e);
        }
    }

    @Override
    public int deleteByExample(Example example) {
        try {
            Method deleteByExample = mapper.getClass().getDeclaredMethod("deleteByExample", example.getClass());
            Object result = deleteByExample.invoke(mapper, example);
            return Integer.parseInt(String.valueOf(result));
        } catch (Exception e) {
            throw new RuntimeException("[deleteByExample]错误", e);
        }
    }

    @Override
    public int deleteByPrimaryKey(Object id) {
        try {
            Method deleteByPrimaryKey = mapper.getClass().getDeclaredMethod("deleteByPrimaryKey", id.getClass());
            Object result = deleteByPrimaryKey.invoke(mapper, id);
            return Integer.parseInt(String.valueOf(result));
        } catch (Exception e) {
            throw new RuntimeException("[deleteByPrimaryKey]错误", e);
        }
    }

    @Override
    public int insert(Record record) {
        try {
            Method insert = mapper.getClass().getDeclaredMethod("insert", record.getClass());
            Object result = insert.invoke(mapper, record);
            return Integer.parseInt(String.valueOf(result));
        } catch (Exception e) {
            throw new RuntimeException("[insert]错误", e);
        }
    }

    @Override
    public int insertSelective(Record record) {
        try {
            Method insertSelective = mapper.getClass().getDeclaredMethod("insertSelective", record.getClass());
            Object result = insertSelective.invoke(mapper, record);
            return Integer.parseInt(String.valueOf(result));
        } catch (Exception e) {
            throw new RuntimeException("[insertSelective]错误", e);
        }
    }

    @Override
    public List<Record> selectByExampleWithBLOBs(Example example) {
        try {
            Method selectByExampleWithBLOBs = mapper.getClass().getDeclaredMethod("selectByExampleWithBLOBs",
                    example.getClass());
            Object result = selectByExampleWithBLOBs.invoke(mapper, example);
            return (List<Record>) result;
        } catch (Exception e) {
            throw new RuntimeException("[selectByExampleWithBLOBs]错误", e);
        }
    }

    @Override
    public List<Record> selectByExample(Example example) {
        try {
            Method selectByExample = mapper.getClass().getDeclaredMethod("selectByExample", example.getClass());
            Object result = selectByExample.invoke(mapper, example);
            return (List<Record>) result;
        } catch (Exception e) {
            throw new RuntimeException("[selectByExample]错误", e);
        }
    }

    @Override
    public Record selectFirstByExample(Example example) {
        try {
            Method selectByExample = mapper.getClass().getDeclaredMethod("selectByExample", example.getClass());
            List<Record> result = (List<Record>) selectByExample.invoke(mapper, example);
            if (null != result && result.size() > 0) {
                return result.get(0);
            }
        } catch (Exception e) {
            throw new RuntimeException("[selectFirstByExample]错误", e);
        }
        return null;
    }

    @Override
    public Record selectFirstByExampleWithBLOBs(Example example) {
        try {
            Method selectByExampleWithBLOBs = mapper.getClass().getDeclaredMethod("selectByExampleWithBLOBs",
                    example.getClass());
            List<Record> result = (List<Record>) selectByExampleWithBLOBs.invoke(mapper, example);
            if (null != result && result.size() > 0) {
                return result.get(0);
            }
        } catch (Exception e) {
            throw new RuntimeException("[selectFirstByExampleWithBLOBs]错误", e);
        }
        return null;
    }

    @Override
    public Record selectByPrimaryKey(Object id) {
        try {
            Method selectByPrimaryKey = mapper.getClass().getDeclaredMethod("selectByPrimaryKey", id.getClass());
            Object result = selectByPrimaryKey.invoke(mapper, id);
            return (Record) result;
        } catch (Exception e) {
            throw new RuntimeException("[selectByPrimaryKey]错误", e);
        }
    }

    @Override
    public int updateByExampleSelective(@Param("record") Record record, @Param("example") Example example) {
        try {
            Method updateByExampleSelective = mapper.getClass().getDeclaredMethod("updateByExampleSelective",
                    record.getClass(), example.getClass());
            Object result = updateByExampleSelective.invoke(mapper, record, example);
            return Integer.parseInt(String.valueOf(result));
        } catch (Exception e) {
            throw new RuntimeException("[updateByExampleSelective]错误", e);
        }
    }

    @Override
    public int updateByExampleWithBLOBs(@Param("record") Record record, @Param("example") Example example) {
        try {
            Method updateByExampleWithBLOBs = mapper.getClass().getDeclaredMethod("updateByExampleWithBLOBs",
                    record.getClass(), example.getClass());
            Object result = updateByExampleWithBLOBs.invoke(mapper, record, example);
            return Integer.parseInt(String.valueOf(result));
        } catch (Exception e) {
            throw new RuntimeException("[updateByExampleWithBLOBs]错误", e);
        }
    }

    @Override
    public int updateByExample(@Param("record") Record record, @Param("example") Example example) {
        try {
            Method updateByExample = mapper.getClass().getDeclaredMethod("updateByExample", record.getClass(),
                    example.getClass());
            Object result = updateByExample.invoke(mapper, record, example);
            return Integer.parseInt(String.valueOf(result));
        } catch (Exception e) {
            throw new RuntimeException("[updateByExample]错误", e);
        }
    }

    @Override
    public int updateByPrimaryKeySelective(Record record) {
        try {
            Method updateByPrimaryKeySelective = mapper.getClass().getDeclaredMethod("updateByPrimaryKeySelective",
                    record.getClass());
            Object result = updateByPrimaryKeySelective.invoke(mapper, record);
            return Integer.parseInt(String.valueOf(result));
        } catch (Exception e) {
            throw new RuntimeException("[updateByPrimaryKeySelective]错误", e);
        }
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(Record record) {
        try {
            Method updateByPrimaryKeyWithBLOBs = mapper.getClass().getDeclaredMethod("updateByPrimaryKeyWithBLOBs",
                    record.getClass());
            Object result = updateByPrimaryKeyWithBLOBs.invoke(mapper, record);
            return Integer.parseInt(String.valueOf(result));
        } catch (Exception e) {
            throw new RuntimeException("[updateByPrimaryKeyWithBLOBs]错误", e);
        }
    }

    @Override
    public int updateByPrimaryKey(Record record) {
        try {
            Method updateByPrimaryKey = mapper.getClass().getDeclaredMethod("updateByPrimaryKey", record.getClass());
            Object result = updateByPrimaryKey.invoke(mapper, record);
            return Integer.parseInt(String.valueOf(result));
        } catch (Exception e) {
            throw new RuntimeException("[updateByPrimaryKey]错误", e);
        }
    }

    @Override
    public int deleteByPrimaryKeys(String ids, Class<?> clazz) {
        try {
            if (StringUtils.isBlank(ids)) {
                return 0;
            }
            String[] idArray = ids.split("-");
            int count = 0;
            for (String idStr : idArray) {
                if (StringUtils.isBlank(idStr)) {
                    continue;
                }
                Method deleteByPrimaryKey = mapper.getClass().getDeclaredMethod("deleteByPrimaryKey", clazz);
                Object result = null;
                if (clazz.getName().equals("java.lang.Integer")) {
                    result = deleteByPrimaryKey.invoke(mapper, Integer.parseInt(idStr));
                } else if (clazz.getName().equals("java.lang.String")) {
                    result = deleteByPrimaryKey.invoke(mapper, idStr);
                }
                count += Integer.parseInt(String.valueOf(result));
            }
            return count;
        } catch (Exception e) {
            throw new RuntimeException("[deleteByPrimaryKeys]错误", e);
        }
    }

    @Override
    public int insertOrUpdate(Record record) {
        try {
            Method insertOrUpdate = mapper.getClass().getDeclaredMethod("insertOrUpdate", record.getClass());
            Object result = insertOrUpdate.invoke(mapper, record);
            return Integer.parseInt(String.valueOf(result));
        } catch (Exception e) {
            throw new RuntimeException("[insertOrUpdate]错误", e);
        }
    }

    @Override
    public int insertOrUpdateSelective(Record record) {
        try {
            Method insertOrUpdateSelective = mapper.getClass().getDeclaredMethod("insertOrUpdateSelective", record.getClass());
            Object result = insertOrUpdateSelective.invoke(mapper, record);
            return Integer.parseInt(String.valueOf(result));
        } catch (Exception e) {
            throw new RuntimeException("[insertOrUpdateSelective]错误", e);
        }
    }

    @Override
    public int updateBatch(List<Record> list) {

        try {
            int count = 0;
            for (Record record : list) {
                count += updateByPrimaryKey(record);
            }
            return count;
        } catch (Exception e) {
            throw new RuntimeException("[updateBatch]错误", e);
        }
    }

    @Override
    public int updateBatchSelective(List<Record> list) {
        try {
            int count = 0;
            for (Record record : list) {
                count += this.updateByPrimaryKeySelective(record);
            }
            return count;
        } catch (Exception e) {
            throw new RuntimeException("[updateBatchSelective]错误", e);
        }
    }

    @Override
    public int batchInsert(List<Record> list) {
        try {
            int count = 0;
            for (Record record : list) {
                count += insert(record);
            }
            return count;
        } catch (Exception e) {
            throw new RuntimeException("[batchInsert]错误", e);
        }
    }

    @Override
    public int batchInsertSelective(List<Record> list) {
        try {
            int count = 0;
            for (Record record : list) {
                count += insertSelective(record);
            }
            return count;
        } catch (Exception e) {
            throw new RuntimeException("[batchInsertSelective]错误", e);
        }
    }

    @Override
    public void initMapper() {
        this.mapper = SpringUtil.getBean(getMapperClass());
    }

    /**
     * 获取类泛型class
     *
     * @return
     */
    public Class<Mapper> getMapperClass() {
        return (Class<Mapper>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}


